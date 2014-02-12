This programs reads a file, makes a compressed suffix trie out of it and retrieves all matching patterns for a given pattern.

The pattern can be of the following forms:

string

{string}*{string} {,int}  // int limits the maximum size of * in the results

{string}[?]{string}


where {} denotes optional arguments and [] denotes arguments which can occur multiple times.



