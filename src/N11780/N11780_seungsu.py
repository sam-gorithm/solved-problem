# n(1 ≤ n ≤ 100)개의 도시, 한 도시에서 출발하여 다른 도시에 도착하는 m(1 ≤ m ≤ 100,000)개의 버스가 있다.
# 각 버스는 한 번 사용할 때 필요한 비용이 있다.

# 모든 도시의 쌍 (A, B)에 대해서 도시 A에서 B로 가는데 필요한 비용의 최솟값을 구하라.


# 첫째 줄에 도시의 개수 n이 주어지고 둘째 줄에는 버스의 개수 m이 주어진다.
# 셋째 줄부터 m+2줄까지 다음과 같은 버스의 정보가 주어진다.
# 처음에는 그 버스의 출발 도시의 번호가 주어진다.
# 버스의 정보는 버스의 시작 도시 a, 도착 도시 b, 한 번 타는데 필요한 비용 c로 이루어져 있다.

# 출력은 참고하셈.


n = int(input())
m = int(input())

# 무한대를 의미하는 큰 수로 초기화
INF = float('inf')

# 그래프 초기화
graph = [[INF] * (n + 1) for _ in range(n + 1)]

# 자기 자신으로 가는 비용은 0으로 설정
for i in range(1, n + 1):
    graph[i][i] = 0

# 버스 정보 입력
for _ in range(m):
    a, b, c = map(int, input().split())
    # 여러 개의 버스가 있을 수 있으므로, 최소 비용으로 갱신
    if graph[a][b] > c:
        graph[a][b] = c

# 경로 복원을 위한 next 배열 생성
# next_city[i][j]는 i에서 j로 가는 최단 경로의 다음 도시를 저장
next_city = [[0] * (n + 1) for _ in range(n + 1)]
for i in range(1, n + 1):
    for j in range(1, n + 1):
        if i != j and graph[i][j] != INF:
            # 초기에는 직접 연결된 다음 도시가 j
            next_city[i][j] = j

# 플로이드-워셜 알고리즘 수행 (경로 추적 포함)
# k: 경로의 중간 경유지, i: 출발지, j: 도착지
for k in range(1, n + 1):
    for i in range(1, n + 1):
        for j in range(1, n + 1):
            # 기존 경로보다 k를 거쳐가는 경로가 더 짧으면 갱신
            if graph[i][j] > graph[i][k] + graph[k][j]:
                graph[i][j] = graph[i][k] + graph[k][j]
                # 최단 경로 갱신되면, 다음 도시도 갱신
                next_city[i][j] = next_city[i][k]

# n개의 줄 출력
# i번째 줄에 출력하는 j번째 숫자는 도시 i에서 j로 가는데 필요한 최소 비용
# i에서 j로 갈 수 없는 경우에는 그 자리에 0을 출력
for i in range(1, n + 1):
    for j in range(1, n + 1):
        if graph[i][j] == INF:
            print(0, end=' ')
        else:
            print(int(graph[i][j]), end=' ')
    print()


# n×n개의 줄을 출력
# i×n+j번째 줄에는 도시 i에서 도시 j로 가는 최소 비용에 포함되어 있는 도시의 개수 k를 출력
# 도시 i에서 도시 j로 가는 경로를 공백으로 구분해 출력. 이때, 도시 i와 도시 j도 출력
# i에서 j로 갈 수 없는 경우에는 0을 출력
for i in range(1, n + 1):
    for j in range(1, n + 1):
        # 경로가 없거나 같은 도시면 0 출력
        if graph[i][j] == INF or i == j:
            print(0)
        else:
            # next_city를 이용하여 최단 경로 복원
            path = []
            current = i
            # 출발지에서 도착지까지 경로를 따라가며 도시들을 기록
            while current != j:
                path.append(current)
                current = next_city[current][j]
            # 마지막 도시(도착지) 추가
            path.append(j)
            # 경로에 포함된 도시의 개수와 경로 출력
            print(len(path), ' '.join(map(str, path)))
