Meta:
@author Jacek Spolnik

Narrative:
As a terminal user
I want to remove empty directory
So that I can do a cleanup

Scenario: rmdir command is run
for /home/test working directory
with /home/test/temp directory as argument
Given a terminal service with working directory set to /home/test
And newly created temp directory
When I run rmdir temp command
Then I am able to confirm /home/test/temp directory is removed

Given a terminal service with working directory set to /home/test
And newly created /home/test/temp directory
When I run rmdir /home/test/temp command
Then I am able to confirm /home/test/temp directory is removed