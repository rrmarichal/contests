namespace MinDiameterOfMergedTrees;

class TreeNode
{
    public required List<int> Neighbours { get; set; }

    public Dictionary<int, int> ParentLongestPath { get; set; }

    public ChildInfo? MaxChild { get; set; }

    public ChildInfo? SecondMaxChild { get; set; }

    public int Diameter { get; internal set; }
}

class ChildInfo
{
    public int Node { get; set; }

    public int Depth { get; set; }
}

class Solution
{
    private TreeNode[] BuildTree(int[][] edges)
    {
        var tree = new TreeNode[edges.Length + 1];

        foreach (var edge in edges)
        {
            if (tree[edge[0]] == null)
            {
                tree[edge[0]] = new TreeNode { Neighbours = [] };
            }

            if (tree[edge[1]] == null)
            {
                tree[edge[1]] = new TreeNode { Neighbours = [] };
            }

            tree[edge[0]].Neighbours.Add(edge[1]);
            tree[edge[1]].Neighbours.Add(edge[0]);
        }

        return tree;
    }

    private int DFS(TreeNode[] tree, int node, int? parent)
    {
        ChildInfo? maxChild = null;
        ChildInfo? secondMaxChild = null;

        if (tree[node] == null)
        {
            return 0;
        }

        foreach (var child in tree[node].Neighbours)
        {
            if (child == parent)
            {
                continue;
            }

            var depth = 1 + DFS(tree, child, node);

            if (maxChild == null || depth > maxChild.Depth)
            {
                secondMaxChild = maxChild;
                maxChild = new ChildInfo { Node = child, Depth = depth };
            }
            else if (secondMaxChild == null || depth > secondMaxChild.Depth)
            {
                secondMaxChild = new ChildInfo { Node = child, Depth = depth };
            }
        }

        tree[node].MaxChild = maxChild;
        tree[node].SecondMaxChild = secondMaxChild;

        return maxChild != null ? maxChild.Depth : 0;
    }

    private void TreeDiameters(TreeNode[] tree, int node, int? parent)
    {
        if (tree[node] == null)
        {
            tree[node] = new TreeNode { Diameter = 0, Neighbours = [] };
            return;
        }

        if (tree[node].ParentLongestPath == null)
        {
            tree[node].ParentLongestPath = [];
        }

        // System.Console.WriteLine("Enter parent: {0}, node: {1}", parent, node);

        if (parent.HasValue)
        {
            foreach (var child in tree[node].Neighbours)
            {
                if (child == parent)
                {
                    continue;
                }

                // System.Console.WriteLine("parent: {0}, node: {1}, child: {2}", parent, node, child);

                tree[node].ParentLongestPath.Add(child, 1 + tree[parent.Value].ParentLongestPath[node]);
                if (tree[node].MaxChild.Node != child)
                {
                    tree[node].ParentLongestPath[child] = Math.Max(
                        tree[node].ParentLongestPath[child],
                        tree[node].MaxChild.Depth
                    );
                }
                else if (tree[node].SecondMaxChild != null)
                {
                    tree[node].ParentLongestPath[child] = Math.Max(
                        tree[node].ParentLongestPath[child],
                        tree[node].SecondMaxChild.Depth
                    );
                }
            }

            // if (tree[parent.Value].ParentLongestPath.ContainsKey(node))
            // {
            tree[node].Diameter = 1 + tree[parent.Value].ParentLongestPath[node];
            // }
            if (tree[node].MaxChild != null)
            {
                tree[node].Diameter = Math.Max(tree[node].Diameter, tree[node].MaxChild.Depth);
            }
        }
        else
        {
            foreach (var child in tree[node].Neighbours)
            {
                if (child == parent)
                {
                    continue;
                }

                tree[node].ParentLongestPath.Add(child, 0);
                if (tree[node].MaxChild.Node != child)
                {
                    tree[node].ParentLongestPath[child] = tree[node].MaxChild.Depth;
                }
                else if (tree[node].SecondMaxChild != null)
                {
                    tree[node].ParentLongestPath[child] = tree[node].SecondMaxChild.Depth;
                }
            }

            tree[node].Diameter = tree[node].MaxChild.Depth;
        }

        foreach (var child in tree[node].Neighbours)
        {
            if (child != parent)
            {
                TreeDiameters(tree, child, node);
            }
        }
    }

    public int MinimumDiameterAfterMerge(int[][] edges0, int[][] edges1)
    {
        var tree0 = BuildTree(edges0);
        var tree1 = BuildTree(edges1);

        DFS(tree0, 0, null);
        DFS(tree1, 0, null);

        TreeDiameters(tree0, 0, null);
        TreeDiameters(tree1, 0, null);

        // for (var node = 0; node < tree0.Length; node++)
        // {
        //     System.Console.WriteLine("tree0 node: {0}, diameter: {1}", node, tree0[node].Diameter);
        // }

        // System.Console.WriteLine();

        // for (var node = 0; node < tree1.Length; node++)
        // {
        //     System.Console.WriteLine("tree1 node: {0}, diameter: {1}", node, tree1[node].Diameter);
        // }

        return Math.Max(
            1 + tree0.Min(node => node.Diameter) + tree1.Min(node => node.Diameter),
            Math.Max(tree0.Max(node => node.Diameter), tree1.Max(node => node.Diameter))
        );
    }

    static void Main(string[] args)
    {
        var solution = new Solution();

        // var edges0 = new int[][] {
        //     [0,1],[0,2],[0,3]
        // };
        // var edges1 = new int[][] {
        //     [0,1]
        // };

        var edges0 = new int[][] {
            [0,1],[0,2],[0,3],[2,4],[2,5],[3,6],[2,7]
        };
        var edges1 = new int[][] {
            [0,1],[0,2],[0,3],[2,4],[2,5],[3,6],[2,7]
        };

        // var edges0 = new int[][] {
        //     [0,1],[0,2],[0,3],[2,4],[2,5],[3,6],[2,7],[7,8],[7,9],[10,7],[10,11],[11,12]
        // };
        // var edges1 = new int[][] {
        //     [0,1],[0,2],[0,3],[2,4],[2,5],[3,6],[2,7]
        // };

        // var edges0 = new int[][] {
        // };
        // var edges1 = new int[][] {
        // };

        // var edges0 = new int[][] {
        //     [0,1],[2,0],[3,2],[3,6],[8,7],[4,8],[5,4],[3,5],[3,9]

        // };
        // var edges1 = new int[][] {
        //     [0,1],[0,2],[0,3]
        // };

        Console.WriteLine(solution.MinimumDiameterAfterMerge(edges0, edges1));
    }
}
