Meta:
@author Jacek Spolnik

Narrative:
As a terminal user
I want to change current directory
So that I can work in context of newly set working directory

Scenario: cd command is run for with /home/test/go_here directory as argument
Given a terminal service with working directory set to /home/test
When I run cd command with /home/test/go_here directory name as argument
Then pwd command should return string equals to /home/test/go_here

Scenario: cd command is run for /home/test working directory with go_here directory as argument
Given a terminal service with working directory set to /home/test
When I run cd command with go_here directory name as argument
Then pwd command should return string equals to /home/test/go_here