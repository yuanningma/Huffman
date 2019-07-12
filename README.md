# Huffman

Hello! This is a demonstration of the Huffman compression technique, which compresses a string/text document into something that
requires less memory to store. The basic concept is that we are essentially creating a character trie; we map every character in
the document/string to its count and putting them all into a PriorityQueue based on count. We remove the two nodes with the lowest
priority every time and set them as the left and right children of a new node with a '\0' null character, whose count is the combined
count of its two children. We then put this new node into the PriorityQueue.

Once we've finished everything and are left with only a single node, we have finished our tree. However, we need a way to decode this
now, as this Huffman tree is useless if we cannot access its information. We therefore traverse the tree again to find "paths" to each
of the characters, using a 0 for going left and a 1 for going right. We map each character to its path in a separate map, and keep track
of these for decoding. We then iterate through the fed-in string/text, replacing every character with the path to the character in our
tree and using this for decoding purposes.

If we want to decode, we need that path-encoded version of the String/text file, as well as the map of the paths. We then simply iterate
through the path string and go left at every 0 and right at every 1, resetting to the root every time we hit a character. When we're
finished, we have the original String/text file back. Yay!

Note that in my demonstration, I use Strings for my path representation, which kind of defeats the whole purpose of this; we don't want
to use characters, as those are a costly 8 bits each. Ideally, this should be a binary representation to save on the cost of characters.
