Meta:
@author Jacek Spolnik

Narrative:
As a terminal user
I want to print name of working directory
So that I can know where I am

Scenario: pwd command is run for /home/test working directory
Given a terminal service with working directory set to /home/test
When I run pwd command to print working directory
Then it should return string equals to /home/test

Scenario: pwd command is run for /sample/dir/to/test working directory
Given a terminal service with working directory set to /sample/dir/to/test
When I run pwd command to print working directory
Then it should return string equals to /sample/dir/to/test