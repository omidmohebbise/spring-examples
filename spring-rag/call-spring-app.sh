curl  "http://localhost:8000/health"
echo "\n"
curl "http://localhost:8000/v1/models"
echo "\n"
echo '***********'
echo "\n"
curl -G "http://localhost:8080/life-principles" \
  --data-urlencode "age=27" \
  --data-urlencode "question=How should I think about money?"