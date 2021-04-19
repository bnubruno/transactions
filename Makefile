# create the container with postgres
compose-up:
	docker-compose -f docker-compose.yml up

# destroy the container with postgres
compose-down:
	docker-compose -f docker-compose.yml down

compose-up-dev:
	docker-compose -f docker-compose.development.yml up

# destroy the container with postgres
compose-down:
	docker-compose -f docker-compose.development.yml down