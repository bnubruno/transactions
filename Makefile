# create the container with postgres
up:
	docker-compose -f docker-compose.yml up

# destroy the container with postgres
down:
	docker-compose -f docker-compose.yml down

up-dev:
	docker-compose -f docker-compose.development.yml up

jar:
	./gradlew build

image:
	docker build . -t app
