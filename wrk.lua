-- wrk -t26 -c600 -d25s http://localhost:8080/are-you-ready -s wrk.lua
wrk.method = "POST"
wrk.body = '{"activity": "partying"}'
wrk.headers["Content-Type"] = "application/json"
