<!--

    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.

-->

This directory contains integration tests for Pulsar.

The integration tests use a framework called [Arquillian Cube](https://github.com/arquillian/arquillian-cube) to bring up a bunch of docker containers running Pulsar services. TestNG can then be used to test functionallity against these containers.

Arquillian sets up a clean set of containers for each test suite. However, if maven is configured to fork per test class, Arquillian will set up a clean set of containers _per test class_. The test cases within the class will share the environment, so tests which leave the cluster in a different state after running should be put in their own test class.

The tests require that docker is installed and running. Tests will only run if the integrationTests system property is defined. To run the tests:
```shell
# in the top level directory
pulsar/ $ mvn install -DskipTests -Pdocker # builds the docker images
...
pulsar/ $ mvn -f tests/pom.xml test -DintegrationTests
```

The directories are as follows:

- docker-images/ : Docker images for integration testing.
- integration/ : The integration tests themselves.
- integration-tests-base/ : A base module for integration test modules. Contains common settings and dependencies.
- integration-tests-topologies/ : Arquillian cluster definitions for use in integration tests.
- integration-tests-utils/ : Utilities for working with arquillian test clusters.

