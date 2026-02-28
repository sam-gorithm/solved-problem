# 첫째 줄의 각 칸에는 정수 1, 2, …, N이 차례대로 들어 있고 둘째 줄의 각 칸에는 1이상 N이하인 정수가 들어 있다.
# 첫째 줄에서 숫자를 적절히 뽑으면, 그 뽑힌 정수들이 이루는 집합과
# 뽑힌 정수들의 바로 밑의 둘째 줄에 들어있는 정수들이 이루는 집합이 일치한다.

# 이러한 조건을 만족시키도록 정수들을 뽑되, 최대로 많이 뽑는 방법을 찾는 프로그램을 작성

# 첫째 줄에는 N(1 ≤ N ≤ 100)을 나타내는 정수 하나가 주어진다. 그 다음 줄부터는 표의 둘째 줄에 들어가는 정수들이 순서대로 한 줄에 하나씩 입력

# 첫째 줄에 뽑힌 정수들의 개수를 출력하고, 그 다음 줄부터는 뽑힌 정수들을 작은 수부터 큰 수의 순서로 한 줄에 하나씩 출력


# 사이클을 찾는 문제로 풀이

# 각 노드에서 시작해서 따라가면 사이클이 생기는데, 그 사이클에 속한 노드들이 뽑힌 정수들이 된다.
# 사이클에 속한 노드들은 서로 연결되어 있기 때문에, 그 중 하나를 뽑으면 나머지 노드들도 뽑히게 된다.
# 따라서 사이클에 속한 노드들을 모두 뽑는 것이 최적의 방법이 된다.

N = int(input())
arr = [0] + [int(input()) for _ in range(N)]  # 1-index

visited = [False] * (N + 1) # 방문 여부를 체크하는 배열, 1-index
result = set() # 뽑힌 정수들을 저장하는 집합, 중복 방지 위해 set 사용

# 각 시작점에서 사이클 찾기
for i in range(1, N + 1):
    if visited[i]: # 이미 방문한 노드면 넘어감
        continue
    
    # 사이클을 찾을 때까지 따라가면서 경로를 저장
    # 사이클이 시작되는 위치를 찾기 위해 positions 딕셔너리를 사용하여 각 노드가 경로에서 처음 등장하는 위치를 기록
    # 사이클이 시작되는 위치를 찾으면, 그 위치부터 끝까지가 사이클이 된다.
    # 사이클에 속한 노드들을 result 집합에 추가하고, 방문 처리

    # 사이클을 찾는 과정에서 이미 방문한 노드를 만나면 사이클이 완성된 것이므로,
    # 그 시점에서 사이클에 속한 노드들을 result에 추가하고 방문 처리

    path = []   # 현재 경로를 저장하는 리스트
    current = i # 현재 노드, 시작점에서부터 따라가면서 업데이트됨
    positions = {}  # 노드가 경로에서 처음 등장하는 위치를 기록하는 딕셔너리, {노드: 경로에서의 인덱스} 형태로 저장
    
    # 사이클을 찾을 때까지 따라가기
    while current not in positions:
        # 현재 노드의 위치를 기록
        positions[current] = len(path)
        # 현재 노드를 경로에 추가
        path.append(current)
        # 다음 노드로 이동
        current = arr[current]
    
    # 사이클 시작 위치
    cycle_start_idx = positions[current]
    # 사이클에 속한 노드들
    cycle = path[cycle_start_idx:]
    
    # 사이클에 속한 노드들만 result에 추가
    for node in cycle:
        result.add(node)
        visited[node] = True


result = sorted(list(result))

print(len(result))

for num in result:
    print(num)