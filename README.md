# Transactions: Rotina de Transações

* Cada portador de cartão (cliente) possui uma conta com seus dados.
* A cada operação realizada pelo cliente uma transação é criada e associada à sua respectiva conta.
* Cada transação possui um tipo (compra a vista, compra parcelada, saque ou pagamento), um valor e uma data de criação.
* Transações de tipo compra e saque são registradas com valor negativo, enquanto
* transações de pagamento são registradas com valor positivo

# Pré-requisitos

- [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Gradle](https://gradle.org/install/) or use Wrapper (./gradlew)
- [Docker](https://docs.docker.com/install/) & [Docker-compose](https://docs.docker.com/compose/install/)

## Packaging

### Makefile

    > make jar -- to build and generates .jar
    > make docker-build -- to generate an app image
    > make start -- to start enviroment dependencies

### Gradle & Docker

    > ./gradlew build -- to build and generates .jar
    > docker build . -t app -- to generate an app image
    > docker-compose -f docker-compose.yml up -- to start enviroment dependencies

### Gradle & Docker Development

    > make start-dev -- to start enviroment dependencies without app