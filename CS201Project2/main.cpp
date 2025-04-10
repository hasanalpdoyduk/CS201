#include <iostream>
#include <vector>
#include <string>
#include <fstream>
#include <sstream>


using namespace std;


struct TrieNode {
    char letter;
    bool isEndOfWord;
    vector<TrieNode*> children;

    TrieNode(char letter) {
        this->letter = letter;
        this->isEndOfWord = false;
        this->children.assign(26, nullptr);
    }
};

class Trie {
private:
    TrieNode* root;

public:
    Trie() {
        root = new TrieNode('\0');
    }

    // word addition to trie
    void insert(const string& word) {
        TrieNode* current = root;
        for (char letter : word) {
            int index = letter - 'a';
            if (current->children[index] == nullptr) {
                current->children[index] = new TrieNode(letter);
            }
            current = current->children[index];
        }
        current->isEndOfWord = true;
    }

    // search
    bool search(const string& word) {
        TrieNode* current = root;
        for (char letter : word) {
            int index = letter - 'a';
            if (current->children[index] == nullptr) {
                return false;
            }
            current = current->children[index];
        }
        return current->isEndOfWord;
    }

    // find words with given prefix
    vector<string> findWordsWithPrefix(const string& prefix) {
        TrieNode* current = root;
        vector<string> words;
        for (char letter : prefix) {
            int index = letter - 'a';
            if (current->children[index] == nullptr) {
                return words;
            }
            current = current->children[index];
        }

        dfs(current, prefix, words);
        return words;
    }

private:
    void dfs(TrieNode* node, string currentWord, vector<string>& words) {
        if (node->isEndOfWord) {
            words.push_back(currentWord);
        }
        for (int i = 0; i < 26; i++) {
            if (node->children[i] != nullptr) {
                dfs(node->children[i], currentWord + (char)(i + 'a'), words);
            }
        }
    }
};


int main() {
    Trie trie;

    vector<string> words = {"ace", "acid", "art", "go", "god", "good", "gosh", "gossip", "great", "greed", "greek"};

    for (const string &word: words) {
        trie.insert(word);
    }

    // input file
    std::ifstream infilestream;
    std::string line;

    infilestream.open("input.txt");
    while (infilestream) {
        std::getline(infilestream, line);
        cout << line << "\n";
    }

    // Input file
    ifstream infilestream("input.txt");
    if (!infilestream.is_open()) {
        cerr << "Error opening input file." << endl;
        return 1;
    }

    int numQueries;
    infilestream >> numQueries;
    infilestream.ignore(); // Ignore newline after numQueries

    ofstream outputFile("output.txt");
    if (!outputFile.is_open()) {
        cerr << "Error opening output file." << endl;
        return 1;
    }

    // Process each query
    for (int i = 0; i < numQueries; ++i) {
        string line;
        getline(infilestream, line);

        stringstream ss(line);
        string op, word;
        ss >> op >> word;

        if (op == "search") {
            outputFile << (trie.search(word) ? "true" : "false") << endl;
        } else if (op == "prefix") {
            vector<string> wordsWithPrefix = trie.findWordsWithPrefix(word);
            if (wordsWithPrefix.empty()) {
                outputFile << "No words found with prefix " << word << endl;
            } else {
                outputFile << "Words with prefix " << word << ":" << endl;
                for (const string& word : wordsWithPrefix) {
                    outputFile << word << " ";
                }
                outputFile << endl;
            }
        }
    }

    infilestream.close();
    outputFile.close();

    return 0;
}




/*
    string searchWord = "god";
    if (trie.search(searchWord)) {
        cout << "true" << endl;
    } else {
        cout << "false" << endl;
    }

    string prefix = "go";
    vector<string> wordsWithPrefix = trie.findWordsWithPrefix(prefix);
    if (wordsWithPrefix.empty()) {
        cout << "No words found with prefix " << prefix << endl;
    } else {
        cout << "Words with prefix " << prefix << ":" << endl;
        for (const string& word : wordsWithPrefix) {
            cout << word << endl;
        }
    }

    return 0;

    */
