def solution(citations):
    citations.sort()
    length = len(citations)
    for i in range(length):
        if length - i <= citations[i] :
            return length - i
    return 0