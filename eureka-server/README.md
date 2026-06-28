# TurnQuest Life — Insurance Management System

A Life Insurance backend built with Spring Boot microservices.

## Services
| Service | Port | Description |
|---|---|---|
| eureka-server | 8761 | Service registry |
| api-gateway | 8080 | Single entry point |
| auth-service | 8081 | Authentication & JWT |
| client-service | 8082 | Policyholder management |
| policy-service | 8083 | Policy administration |
| receipts-service | 8084 | Premium receipting |
| claims-service | 8085 | Claims processing |

## Running locally
Start services in this order:
1. `eureka-server`
2. `auth-service`
3. `client-service`
4. `api-gateway`
5. `policy-service`
6. `receipts-service`
7. `claims-service`
