import sys

N = int(sys.stdin.readline())
stack = []
for _ in range(N):
    used = sys.stdin.readline().split()
    if used[0] == 'push':
        stack.append(int(used[1]))
    else:
        if used[0] == 'pop':
            output = stack.pop() if len(stack) > 0 else -1
        elif used[0] == 'size':
            output = len(stack)
        elif used[0] == 'empty':
            output = 0 if len(stack) > 0 else 1
        else:
            output = stack[-1] if len(stack) > 0 else -1
        print(output)
