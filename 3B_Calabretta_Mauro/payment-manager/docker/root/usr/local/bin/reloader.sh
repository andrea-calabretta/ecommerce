#!/bin/sh

load() {
  mvn package
  java -jar target/*.jar &
  #la & mi permette di eseguire il comando in un processo separato e quindi il controllo
  # ritorna subito al comando successivo e non va in blocco la shell
}

killer() {
  killall -9 java || echo "NO PROCESS FOUND... Ignoring"
}

watch() {
  #inotifywait guarda i cambiamenti che avvengono nei file che gli passiamo come argomenti
  #e in generale ritorna dopo che un evento avviene su quel file

  #il flag "-m" sta per monitor e consente di stampare i cambaimenti che sono avveuti su quel
  #file e fa ricominciare il controllo di inotifywait all'infinito

  #il flag "-r" sta per recursive poichè questo controllo avviene dentro tutta la directory
  # in cui mi trovo, quindi ./

  #il flag "-e" sta per esclude e mi serve per escludere il monitoraggio di file che non mi interessano
  # quindi escludo la cartella docker, la cartella mvn e la cartella target

  inotifywait -m -r ./ -e close_write -e delete --excludei "(docker|.mvn|target)" | \
 # path,ation e file sono 3 variabilli restituite da inotifywait
  while read path action file; do
    #controllo che ci siano dei cambiamenti che possono triggerare la rebuild
    if echo "$file" | grep -q -E ".*\(.properties|.java|.xml\)$"; then
      echo "Detected a change into $path/$file: $action"
      echo "Reloading..."
      killer
      load
    fi
  done
}

killer
#/usr/local/bin/wait-mongo.sh
load
watch
