Meta:
@author Jacek Spolnik

Narrative:
As a terminal user
I want to print name of working directory
So that I can know where I am

Scenario: pwd command is run for \home\test working directory
Given working directory name equals to \home\test
And a terminal service
When I run pwd command to print working directory
Then it should return string equal to \home\test

Scenario: pwd command is run for \sample\dir\to\test working directory
Given working directory name equals to \sample\dir\to\test
And a terminal service
When I run pwd command to print working directory
Then it should return string equal to \sample\dir\to\test