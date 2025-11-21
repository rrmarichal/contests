namespace TopDownMinDiameterOfMergedTrees;

class NodeInfo
{
  public int Height { get; set; }

  public int Diameter { get; set; }
}

public class TopDownSolution
{
  private List<int>[] BuildTree(int[][] edges)
  {
    var tree = new List<int>[edges.Length + 1];

    foreach (var edge in edges)
    {
      if (tree[edge[0]] == null)
      {
        tree[edge[0]] = [];
      }

      if (tree[edge[1]] == null)
      {
        tree[edge[1]] = [];
      }

      tree[edge[0]].Add(edge[1]);
      tree[edge[1]].Add(edge[0]);
    }

    return tree;
  }

  private NodeInfo TreeDiameter(List<int>[] tree, int node, int? parent)
  {
    if (tree.Length == 1)
    {
      return new NodeInfo { Diameter = 0, Height = 0 };
    }

    var max0 = 0;
    var max1 = 0;
    var maxD = 0;

    foreach (var child in tree[node])
    {
      if (child != parent)
      {
        var nodeInfo = TreeDiameter(tree, child, node);

        if (nodeInfo.Height + 1 > max0)
        {
          max1 = max0;
          max0 = nodeInfo.Height + 1;
        }
        else if (nodeInfo.Height + 1 > max1)
        {
          max1 = nodeInfo.Height + 1;
        }

        if (nodeInfo.Diameter > maxD)
        {
          maxD = nodeInfo.Diameter;
        }
      }
    }

    return new NodeInfo { Height = max0, Diameter = Math.Max(max0 + max1, maxD) };
  }

  public int MinimumDiameterAfterMerge(int[][] edges0, int[][] edges1)
  {
    var tree0 = BuildTree(edges0);
    var tree1 = BuildTree(edges1);

    var diameter0 = TreeDiameter(tree0, 0, null);
    var diameter1 = TreeDiameter(tree1, 0, null);

    return Math.Max(
      Math.Max(diameter0.Diameter, diameter1.Diameter),
      1 + (diameter0.Diameter + 1) / 2 + (diameter1.Diameter + 1) / 2
    );
  }

  public static void Main(string[] args)
  {
    var solution = new TopDownSolution();
    // var edges0 = new int[][] {
    //     [0,1],[0,2],[0,3]
    // };
    // var edges1 = new int[][] {
    //     [0,1]
    // };

    // var edges0 = new int[][] {
    //   [0,1],[0,2],[0,3],[2,4],[2,5],[3,6],[2,7]
    // };
    // var edges1 = new int[][] {
    //   [0,1],[0,2],[0,3],[2,4],[2,5],[3,6],[2,7]
    // };

    // var edges0 = new int[][] {
    //     [0,1],[0,2],[0,3],[2,4],[2,5],[3,6],[2,7],[7,8],[7,9],[10,7],[10,11],[11,12]
    // };
    // var edges1 = new int[][] {
    //     [0,1],[0,2],[0,3],[2,4],[2,5],[3,6],[2,7]
    // };

    // var edges0 = new int[][] { };
    // var edges1 = new int[][] { };

    var edges0 = new int[][] {
      [0,1],[2,0],[3,2],[3,6],[8,7],[4,8],[5,4],[3,5],[3,9]
    };
    var edges1 = new int[][] {
      [0,1],[0,2],[0,3]
    };

    Console.WriteLine(solution.MinimumDiameterAfterMerge(edges0, edges1));
  }
}
