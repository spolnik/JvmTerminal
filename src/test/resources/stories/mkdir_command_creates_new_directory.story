Meta:
@author Jacek Spolnik

Narrative:
As a terminal user
I want to create new directory
So that I can use it later

Scenario: mkdir command is run
for /home/test working directory
with /home/test/go_here directory as argument
Given a terminal service with working directory set to /home/test
When I run mkdir /home/test/go_here command
Then changing directory to newly created one should success
And pwd command should return string equals to /home/test/go_here

Scenario: mkdir command is run
for /home/test working directory
with go_here directory as argument
Given a terminal service with working directory set to /home/test
When I run mkdir go_here command
Then changing directory to newly created one should success
And pwd command should return string equals to /home/test/go_here