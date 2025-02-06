
Feature: Validating Place Api's

Scenario: Verify if Place is being Succesfully added using AddedPlaceAPI
	Given Add Place Payload
	When user calls "AddPPlaceAPI" with post http request
	Then the API call got sucesss with status code 200
	And "status" in response body is "ok"
	And "Scope" in response body is "App"