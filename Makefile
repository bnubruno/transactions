# create the container with postgres
start:
	docker-compose -f docker-compose.yml up

# destroy the container with postgres
down:
	docker-compose -f docker-compose.yml down

start-dev:
	docker-compose -f docker-compose.development.yml up

jar:
	./gradlew build

docker-build:
	docker build . -t app
