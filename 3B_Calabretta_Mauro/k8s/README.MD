# Distributed Systems & Big Data 2024 

## Andrea Calabretta

### Istruzioni per il deploy su k8s

Se precedentemente è stato creato un cluster di nome "my-cluster", eliminiamolo con:
```sh
kind delete cluster --name my-cluster
```
Creaiamo il nuovo cluster secondo quanto presente nel file di configurazione:

kind create cluster --config=config.yml
```sh
kind create cluster --config=config.yml
```
Configuriamo NGINX come Ingress, seguendo la configurazione della documentazione ufficiale:

```sh
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/master/deploy/static/provider/kind/deploy.yaml
```
Creiamo 2 namespace che ci serviranno successivamente:

```sh
kubectl create namespace dsbd
kubectl create namespace monitoring
```
Facciamo l'apply del manifest dell'ingress:
```sh
kubectl apply -f ingress.yml
```
Facciamo l'apply del manifest di kafka:
```sh
kubectl apply -f kafka-exporter.yml -n dsbd
```
Facciamo l'apply del manifest del database mongo:

```sh
kubectl apply -f paymentdb.yml -n dsbd
```
All'interno del pod del database, creiamo l'utente admin che ci serve per essere coerenti con il resto delle configurazioni:

```sh
kubectl exec -it paymentdb-0 -n dsbd -- /bin/bash
mongo
use admin

db.createUser(
  {
    user: "root",
    pwd: "toor",
    roles: [ { role: "root", db: "admin" } ]
  }
)
```

Ora creiamo il pod che gestisce i pagamenti:

```sh
kubectl apply -f micropayment.yml -n dsbd
```
Monitoriamo tutti i pods del namespace dsbd:

```sh
watch kubectl get pods -n dsbd
```

Ora monitoriamo la coda kafka e i topic orders e logging:

Per entrare dentro il pod di kafka appena creato:
```sh
kubectl exec -it kafka-0 -n dsbd -- /bin/bash
```

avviamo il consumer di kafka sul topic orders:
```sh
kafka-console-consumer --bootstrap-server kafka:9092 --topic orders
```
e lasciamo il terminale aperto (qui vedremo gli orders)

a questo punto facciamo una POST all’endpoint:
```sh
http://localhost:80/payment/ipn
```

Apriamo un altro terminale e avviamo il consumer di kafka sul topic logging:
```sh
kafka-console-consumer --bootstrap-server kafka:9092 --topic logging
```
e lasciamo il terminale aperto (qui vedremo i logging in caso di errore)


#CONFIGURAZIONE PER I METRICS SERVER 
cd load 
kubectl apply -f metrics-server.yml
watch "kubectl get pods -n kube-system | tail -n 2"
watch -n 1 "kubectl get pods -n kube-system | tail -n 5"
#LOCUST e Horizontal Pod Autoscaling (HPA)
cd load
kubectl apply -f micropayment-hpa.yml -n dsbd
watch kubectl get hpa -n dsbd

kubectl get hpa -n dsbd
kubectl get pods -n dsbd
kubectl get pods -n kube-system


# PROMETHEUS E GRAFANA 
helm upgrade prometheus prometheus-community/kube-prometheus-stack --reset-values --namespace=monitoring

helm repo add prometheus-community https://prometheus-community.github.io/helm-charts
helm repo update
helm install prometheus prometheus-community/kube-prometheus-stack --namespace=monitoring


      kubectl --namespace monitoring get pods -l "release=prometheus"
      kubectl --namespace default get pods -l "release=prometheus"



# MONGO DB EXPORTER
helm install mongodb-exporter prometheus-community/prometheus-mongodb-exporter -f values-mongodb-exporter.yml -n monitoring

kubectl get svc -n monitoring 
kubectl get pod -n monitoring
kubectl get servicemonitor -n monitoring
kubectl get servicemonitor mongodb-exporter-prometheus-mongodb-exporter -o yaml -n monitoring

kubectl get svc -n monitoring
kubectl port-forward service/mongodb-exporter-prometheus-mongodb-exporter 9216 -n monitoring

# KAFKA EXPORTER

helm install kafka-exporter prometheus-community/prometheus-kafka-exporter -f values-kafka-exporter.yml -n dsbd

helm uninstall kafka-exporter -n dsbd

kubectl get svc -n dsbd 

kubectl logs kafka-0 -n dsbd -c kafka
kubectl logs kafka-0 -n dsbd -c kafka-exporter
kubectl get pod -n monitoring
helm uninstall mongodb-exporter -n monitoring
helm uninstall kafka-exporter -n monitoring

# PROMETHEUS

https://www.youtube.com/watch?v=mLPg49b33sA

kubectl port-forward service/prometheus-kube-prometheus-prometheus 9090 -n monitoring

localhost:9090

# GRAFANA
kubectl port-forward deployment/prometheus-grafana 3000 -n monitoring

localhost:3000

admin
prom-operator



(per vedere quali sono tutti gli eventi che si sono verificati nel cluster, utile per capire perchè lo scheduler è andato giù)
kubectl get events

