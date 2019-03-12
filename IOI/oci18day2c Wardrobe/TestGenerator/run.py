import sys
import subprocess
from random import randint
import string

def generate_and_run():
    M = randint(1, 20)
    D = randint(1, M)
    L =  6
    range = (L, randint(L + 1, L + 24))
    # Invoke the generator script.
    fn = "input.in".format(M = M, D = D)
    input = open(fn, "w+")
    generatorProcess = subprocess.Popen("python generate.py {M} {D} {L} {H}".format(M = M, D = D, L = range[0], H = range[1]), shell=True, stdout=input, stderr=subprocess.STDOUT)
    retval = generatorProcess.wait()

    print("Invoked generator process with arguments M={M} D={D} range={R}".format(M = M, D = D, R = range))
    f = open(fn, "r")
    print(f.read())

    # Invoke NlgNExpansion solution process
    print("Invoking NlgNExpansion solution")
    s1 = subprocess.Popen('./a.out', shell=True, stdin=open(fn, "r"), stdout=subprocess.PIPE, stderr=subprocess.STDOUT)
    retval = s1.wait()
    o1 = s1.stdout.read()
    
    # Invoke BruteForce solution process
    print("Invoking BruteForce solution")
    s2 = subprocess.Popen('java contests.ioi.App', shell=True, stdin=open(fn, "r"), stdout=subprocess.PIPE, stderr=subprocess.STDOUT)
    retval = s2.wait()
    o2 = s2.stdout.read()

    if (o1 != o2):
        print("KO", o1, o2)
        exit(0)
    else:
        print("OK.")

if __name__ == "__main__":
    while (True):
        generate_and_run()
        # k = input("Press Q(q) to quit. Any other key to run again.")
        # if (k.lower() == "q"):
        #     break
