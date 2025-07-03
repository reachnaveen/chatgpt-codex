# Demo Spring API Service

This repository contains a simple Spring Boot application with an example CI pipeline including SAST and DAST steps. It also demonstrates contract testing with Pact, API testing with REST Assured, mocking with WireMock and basic GitOps resources using Helm and Kustomize for dev, stage and prod environments.

## Building

```bash
mvn verify
```

## Helm Chart

The `helm/` directory contains a basic Helm chart. Kustomize overlays for dev, stage and prod live under `kustomize/overlays`.
