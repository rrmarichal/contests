using System;
using System.Text.RegularExpressions;

public class Solution
{
    public static void Main(string[] args)
    {
        var p = new Solution(); 
        Console.WriteLine(p.ValidIPAddress("1.1.1.01"));
        Console.WriteLine(p.ValidIPAddress("172.16.254.1"));
        Console.WriteLine(p.ValidIPAddress("256.256.256.256"));
        Console.WriteLine(p.ValidIPAddress("2001:0db8:85a3:0000:0000:8a2e:0370:7334"));
        Console.WriteLine(p.ValidIPAddress("2001:db8:85a3:0:0:8A2E:0370:7334"));
        Console.WriteLine(p.ValidIPAddress("2001:0db8:85a3::8A2E:0370:7334"));
        Console.WriteLine(p.ValidIPAddress("32001:0db8:85a3:0000:0000:8a2e:0370:7334"));
    }

    public string ValidIPAddress(string IP)
    {
        if (!IP.Contains(":")) {
            var os = IP.Split(new[] { '.' });
            if (os.Length == 4) {
                foreach (var o in os) {
                    if (o.Length > 3 || o.Length == 0) return "Neither";
                    if (o.StartsWith("0") && o.Length > 1) return "Neither";
                    int x;
                    if (!int.TryParse(o, out x)) return "Neither";
                    if (x == 0 || x > 255) return "Neither";
                }
                return "IPv4";
            }
            return "Neither";
        }
        else {
            var ipv6re = "^[\\da-fA-F]{1,4}:[\\da-fA-F]{1,4}:[\\da-fA-F]{1,4}:[\\da-fA-F]{1,4}:[\\da-fA-F]{1,4}:[\\da-fA-F]{1,4}:[\\da-fA-F]{1,4}:[\\da-fA-F]{1,4}$";
            if (Regex.Match(IP, ipv6re).Success)
                return "IPv6";
            return "Neither";
        }
    }
}
