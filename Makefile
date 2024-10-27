DB_CONTAINER = mysql-db

.PHONY: connect

connect:
	docker exec -it $(DB_CONTAINER) mysql -uroot -proot