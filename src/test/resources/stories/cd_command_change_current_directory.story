Meta:
@author Jacek Spolnik

Narrative:
As a terminal user
I want to change current directory
So that I can work in context of newly set working directory

Scenario: cd command is run
for /home/test working directory
with /home/test/go_here directory as argument
Given a terminal service with working directory set initially to /home/test
When I run cd /home/test/go_here command
Then pwd command should return string equals to /home/test/go_here

Scenario: cd command is run
for /home/test working directory
with go_here directory as argument
Given a terminal service with working directory set initially to /home/test
When I run cd go_here command
Then pwd command should return string equals to /home/test/go_here