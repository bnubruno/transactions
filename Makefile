LOCAL_USERNAME=transaction
LOCAL_PASSWORD=transaction123
LOCAL_DB_URL=jdbc:postgresql://localhost:5434/enotas-issuer

# Run migration for local environment
migration:
	 mvn flyway:migrate -Dflyway.user=$(LOCAL_USERNAME) -Dflyway.url=$(LOCAL_DB_URL) -Dflyway.password=$(LOCAL_PASSWORD)

# Run validate for local environment
validate-migration:
	 mvn flyway:validate -Dflyway.user=$(LOCAL_USERNAME) -Dflyway.url=$(LOCAL_DB_URL) -Dflyway.password=$(LOCAL_PASSWORD)

# create the container with postgres
compose-up:
	docker-compose -f docker-compose.yml up

# destroy the container with postgres
compose-down:
	docker-compose -f docker-compose.yml down

# Start the application locally using postgres docker database
start: compose-down compose-up migration run
