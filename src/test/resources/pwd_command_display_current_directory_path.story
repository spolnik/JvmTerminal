Meta:
@author Jacek Spolnik

Narrative:
As a terminal user
I want to print name of working directory
So that I can know where I am

Scenario: pwd command is run
Given a terminal service
And working directory name equals to \home\test
When I run pwd command to print working directory
Then it should return string equal to \home\test