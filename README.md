Entrata Automation
It's an automation demo for entrata website. Here we're automating the various features of entrata website and validating it.

Test Cases
Test Case 1: TC-01_Validate_the_entrata_Title

Test Case 2: TC-02_Validate_the_contact_form_in_Residental_Portal

Test Case 3: TC-03_Validate_all_the_links_in_Base_Camp_footer_section_working_or_not.

Test Case 4: TC-04_Validate_Hotel_Website_under_Base_Camp.

Test Case 5: TC-05_Validate_Student_Option_under_Solutions_Tab_is_working_or_not.

FAQ
Question 1: What to do if test case is failed after opening the entrata webpage?
Answer: After opening the Entrata webpage, it is taking longer time to reload the webpage. So, wait untill page is fully loaded. It will work once page is fully loaded.

Question 2: What if the Test is failed in Cookies?
Answer: Cookies is a part of javascript and it is handled by selenium in the given code.

Command to Run the Test
mvn clean

mvn commit

mvn test

mvn verify
