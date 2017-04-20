# Repeated running of a failed scenario (in Jenkins)

The target of the project:
	- Build a new scenario from the failed tests of a previous one
	- Run it again in a Jenkins job

## The content of project

There 2 parts in the project:
	- Scenario builder for building a new scenario from the failed tests of the previous one. The builder implemented for the different Automation frameworks(currently JSystem and Cucumber)
	- The Jenkins plugin for running the new built scenario in a Jenkins job  

## Used resources for implementation:

	- Java for the scenario builder. XML parser for building a JSystem's scenario. JSON parser for building a Cucumber's scenario 
	- Groovy for the Jenkins plugin
