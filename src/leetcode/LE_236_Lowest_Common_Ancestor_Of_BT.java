package leetcode;

import common.TreeNode;

/**
 * Created by yuank on 4/6/18.
 */
public class LE_236_Lowest_Common_Ancestor_Of_BT {
    /**
        Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

        According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as
        the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”

                _______3______
               /              \
            ___5__          ___1__
           /      \        /      \
           6      _2       0       8
                 /  \
                 7   4
        For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. Another example is LCA of nodes 5 and 4 is 5,
        since a node can be a descendant of itself according to the LCA definition.
     */

    /**
     * if both p and q exist in Tree rooted at root, then return their LCA
     * if neither p and q exist in Tree rooted at root, then return null
     * if only one of p or q (NOT both of them), exists in Tree rooted at root, return it
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root;
        }

        return left == null ? right : left;
    }
}
