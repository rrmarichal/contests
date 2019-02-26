from math import gcd as bltin_gcd

def coprimes(a, b):
    return bltin_gcd(a, b) == 1

def coprimes_up_to(n):
    list = []
    for k in range(2, n+1):
        for j in range(1, k):
            if coprimes(j, k):
                list.append((j, k))
    return list

if __name__ == "__main__":
    coprimes = coprimes_up_to(20)
    print(coprimes, len(coprimes))
