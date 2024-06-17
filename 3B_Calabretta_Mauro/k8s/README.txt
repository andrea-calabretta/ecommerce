kind delete cluster --name my-cluster
kind create cluster --config=config.yml
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/master/deploy/static/provider/kind/deploy.yaml
kubectl create namespace dsbd

kubectl create namespace monitoring
kubectl apply -f ingress.yml
kubectl apply -f kafka.yml -n dsbd
kubectl apply -f kafka-exporter.yml -n dsbd

kubectl apply -f paymentdb.yml -n dsbd

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

kubectl apply -f micropayment.yml -n dsbd

watch kubectl get pods -n dsbd


Per entrare dentro il pod di kafka appena creato:
kubectl exec -it kafka-0 -n dsbd -- /bin/bash

e poi avviare il consumer di kafka:
kafka-console-consumer --bootstrap-server kafka:9092 --topic orders
e lasciamo il terminale aperto (qui vedremo gli orders)

a questo punto facciamo una POST all’endpoint:
http://localhost:80/payment/ipn

poi avviamo un altro terminale:
e poi avviare il consumer di kafka
kafka-console-consumer --bootstrap-server kafka:9092 --topic logging

e lasciamo il terminale aperto (qui vedremo i logging in caso di errore)



#CONFIGURAZIONE PER I METRICS SERVER 
cd load 
kubectl apply -f components.yml
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





kubectl apply -f locust-service-monitor.yml -n dsbd
kubectl apply -f load-gen.yml -n dsbd

kubectl apply -f resource-reader.yml
kubectl apply -f components.yml
kubectl apply -f metric-server.yml
kubectl get pods -n kube-system

(per vedere quali sono tutti gli eventi che si sono verificati nel cluster, utile per capire perchè lo scheduler è andato giù)
kubectl get events



sudo kubectl port-forward -n kube-system service/kubernetes-dashboard 10443:443 --address 192.168.1.20
#sudo microk8s kubectl port-forward -n kube-system service/kubernetes-dashboard 10443:443 --address 151.97.13.120
helm install dashboard kubernetes-dashboard/kubernetes-dashboard -n kubernetes-dashboard --create-namespace
kubectl proxy &


###################################################
16:04
sudo microk8s kubectl port-forward -n kube-system service/kubernetes-dashboard 10443:443 --address 151.97.13.120

16:07
kubectl get services --all-namespaces

16:11
helm install dashboard kubernetes-dashboard/kubernetes-dashboard -n kubernetes-dashboard --create-namespace
kubectl proxy

16:18
# Add kubernetes-dashboard repository
helm repo add kubernetes-dashboard https://kubernetes.github.io/dashboard/
# Deploy a Helm Release named "kubernetes-dashboard" using the kubernetes-dashboard chart
helm upgrade --install kubernetes-dashboard kubernetes-dashboard/kubernetes-dashboard --create-namespace --namespace kubernetes-dashboard
https://kubernetes.io/docs/tasks/access-application-cluster/web-ui-dashboard/

16:20
https://github.com/imorti/kind-dashboard-setup
