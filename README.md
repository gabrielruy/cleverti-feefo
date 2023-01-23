This application implements a method to normalize words using Levenshtein Distance.

The Levenshtein distance is also called as Edit distance, is the minimum number of operations required to transform one string to another.
Tipically, three types of operations are performed (one at a time);
1- Replace a character
2- Delete a character
3- Insert a character

For example, the Levenshtein distance between "kitten" and "sitting" is 3 since, at a minimum, 3 edits are required to change one into the other.
1- kitten -> sitten (substitution of "s" for "k")
2- sitten -> sittin (substitution of "e" for "i")
3- sittin -> sitting (insertion of "g" at the end)

Luckily, we already have an implementation of Levenshtein rules ready to be used on Apache Commons Text library.
https://commons.apache.org/proper/commons-text/apidocs/org/apache/commons/text/similarity/LevenshteinDistance.html

The exercise proposed for this application was:
Provided with a list of ideal (normalized) job titles, create a class that implements a process that returns the best match when provided with an
input string.
Concretely, given a normalized job titles list of “Architect", "Software Engineer", "Quantity Surveyor", and "Accountant", write a process that
returns the normalized result for the input.
|       INPUT         |       NORMALIZED      |
| "Java Enginner"     | "Software engineer"   |
| "C# Engineer"       | "Software engineer"   |
| "Accountant"        | "Accountant"          |
| "Chief Accountant"  | "Accountant"          |

To test the method and the application, simply run the tests.