kind create cluster --config=config.yml
kubectl apply -f nginx.yml
kubectl create namespace dsbd
kubectl apply -f ingress.yml
