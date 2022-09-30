### Word Composition Problem

# Steps to execute the code
* After downloading the folder open any idle and wordComposition.java file. 

## Overview
`Word Composition Problem is Similar to word break problem which is done by various approaches like backtracking,DP,Trie data structure. Here I had used Trie data structure as it is the faster searching technique.`

## Approach:
* The problem consisted of traversing strings character by character. This suggested the use of Trie (prefix) tree. This program needs to keep track of all the valid words in a compound word, which can be done conveniently in trie.

* To check if a word is a compound word, the program traverses the string character by character, till it finds a complete word from the trie. It then recursively checks whether the remaining part is also a word OR a compound word.