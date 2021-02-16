"""
Author: Sean Poston
Date: 2/8/2021
Purpose: Perform DFS and BFS (interative and recursive).
"""
class TreeNode:
    #TreeNode object for linking nodes and keeping information.
    def __init__(self, val: int):
        #Construct Node
        self.val = val
        self.left = None
        self.right = None

class Tree:
    #Tree wrapper class to perform on objects
    def __init__(self, val: int):
        #Constructor to initialize the Tree
        self.root = TreeNode(val)

    def insert(self, val: int):
        #Insert Node
        #The Tree will be traversed until it finds a blank spot
        #If the entered value is > the current node, then it will
        #go to the right of the node.
        #If the entered value is < the current node, then it will
        #go to the left of the node.
        current = self.root
        while True:
            if val > current.val and current.right != None:
                current = current.right
            elif val < current.val and current.left != None:
                current = current.left
            elif val > current.val and current.right == None:
                current.right = TreeNode(val)
                break
            elif val < current.val and current.left == None:
                current.left = TreeNode(val)
                break

    def DFSTree(self, node: TreeNode, vals):
        #Will add all node values to a list.
        if node == None:
            return 
        vals.append(node.val) #preorder
        self.DFSTree(node.left, vals)
        #vals.append(node.val) inorder
        self.DFSTree(node.right, vals)
        #vals.append(node.val) postorder

    def displayDFSTree(self):
        #Wrapper function for DFSTree
        vals = []
        self.DFSTree(self.root, vals)
        print(vals)

    def BFSTree(self, node: TreeNode, vals):
        return

    def displayBFSTree(self):
        #Wrapper function for BFSTree
        vals = []
        self.BFSTree(self.root, vals)
        print(vals)
    
def main():
    tree = Tree(5)
    tree.insert(6)
    tree.insert(3)
    tree.insert(4)
    tree.insert(9)
    tree.displayDFSTree()
    tree.displayBFSTree()
main()