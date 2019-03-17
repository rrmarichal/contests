import sys
from random import randint

N = int(sys.argv[1])
M = int(sys.argv[2])
W = int(sys.argv[3])

WW = [randint(1, W) for x in range(1, N+1)]
BB = [randint(1, 1000000) for x in range(1, N+1)]

# Generate connections
D = set()
for x in range(0, M):
    p = (randint(1, N), randint(1, N))
    while p in D or p[0] == p[1]:
        p = (randint(1, N), randint(1, N))
    D.add(p)

print(N, M, W)
for x in WW[0:N-1]:
    print(x, end=' ')
print(WW[N-1])
for x in BB[0:N-1]:
    print(x, end=' ')
print(BB[N-1])
for k in D:
    print(k[0], k[1])
