# Copyright (c) 2016 SUSE LLC
# Licensed under the terms of the MIT license.

Feature: register a salt-minion via bootstrap

  Scenario: bootstrap a centos minion
     Given I am authorized
     When I follow "Salt"
     Then I should see a "Bootstrapping" text
     And I follow "Bootstrapping"
     Then I should see a "Bootstrap Minions" text
     And  I enter the hostname of "ceos-minion" as hostname
     And I enter "22" as "port"
     And I enter "root" as "user"
     And I enter "linux" as "password"
     And I click on "Bootstrap"
     And I wait for "150" seconds
     Then I should see a "Successfully bootstrapped host! Your system should appear in System Overview shortly." text

  Scenario: Run a remote command on centos
    Given I am authorized as "testing" with password "testing"
    And I follow "Salt"
    And I follow "Remote Commands"
    And I should see a "Remote Commands" text
    Then I enter command "cat /etc/os-release"
    And I click on preview
    And I wait for "5" seconds
    And I click on run
    Then I should see "ceos-minion" hostname
    And I wait for "15" seconds
    And I expand the results for "ceos-minion"
    And I should see a "rhel fedora" text
    Then I should see a "REDHAT_SUPPORT_PRODUCT" text

  Scenario: Reboot a salt minion (ssh-managed) (centos)
    Given I am on the Systems overview page of this "ceos-minion"
    When I follow first "Schedule System Reboot"
    Then I should see a "System Reboot Confirmation" text
    And I should see a "Reboot system" button
    And I click on "Reboot system"
    Then I wait and check that "ceos-minion" has rebooted
    And I wait until "salt-minion" service is up and running on "ceos-minion"
