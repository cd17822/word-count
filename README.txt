CS442 Design Patterns
Fall 2016
Assignment 4 README FILE

Due Date: Wednesday, November 23, 2016
Submission Date: Sunday, November 27, 2016
Grace Period Used This Project: 3 Days
Grace Period Remaining: 1 Day
Author: Charles DiGiovanna
e-mail: cdigiov1@binghamon.edu

PURPOSE:

To efficiently count and store words from a file.

PERCENT COMPLETE:

100%

PARTS THAT ARE NOT COMPLETE:

N/A

BUGS:

N/A

SAMPLE OUTPUT:

(from console)

Average Time: 28.0ms.

(from output file)

Word count: 1312.
Distinct word count: 640.
Character count: 6147.

Average preference_score is: 6.0

TO COMPILE:

ant all

TO RUN:

ant run -Darg0=<in-file> -Darg1=<out-file> -Darg2=<num-iterations> -Darg3=<debug-level>

i.e.
ant run -Darg0=io/input.txt -Dargio/output.txt -Darg2=50 -Darg3=2

EXTRA CREDIT:

N/A

BIBLIOGRAPHY:

N/A

ACKNOWLEDGEMENT:

N/A

DATA STRUCTURE JUSTIFICATION:

I used a red-black tree to store distinct words and their frequencies. The self-balancing in the tree guaranteed efficient O(log(n)) insertions and O(log(n)) lookups.

Each node has an ArrayList of observers so when one node is modified, its backups are modified in O(b) time, where b is the number of observers the node has.
