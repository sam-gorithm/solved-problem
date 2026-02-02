# N개의 도시가 있다. 그리고 한 도시에서 출발하여 다른 도시에 도착하는 버스가 M개 있다.
# 우리는 A라는 도시에서 B라는 도시로 가려고 한다.

# C는 버스를 타고 이동하는데 걸리는 시간
# C = 0인 경우는 순간 이동을 하는 경우, C < 0인 경우는 타임머신으로 시간을 되돌아가는 경우

# 시간을 무한히 오래 전으로 되돌릴 수 있거나 경로가 없다면 첫째 줄에 -1을 출력,
# 그렇지 않다면 N-1개 줄에 걸쳐 각 줄에 1번 도시에서 출발해 2번 도시, 3번 도시, ..., N번 도시로 가는 가장 빠른 시간을 순서대로 출력

# 첫째 줄에 도시의 개수 N (1 ≤ N ≤ 500), 버스 노선의 개수 M (1 ≤ M ≤ 6,000)
# 둘째 줄부터 M개의 줄에는 버스 노선의 정보 A, B, C (1 ≤ A, B ≤ N, -10,000 ≤ C ≤ 10,000)


N, M = map(int, input().split())
edges = []

for _ in range(M):
    a, b, c = map(int, input().split())
    edges.append((a - 1, b - 1, c))

INF = float('inf')
# 각 도시까지의 최단 거리 초기화
dist = [INF] * N
dist[0] = 0

# 벨만-포드 알고리즘
for i in range(N - 1):
    # 모든 간선에 대해 완화 작업 수행
    for a, b, c in edges:
        # 현재 도시 a까지의 거리가 무한대가 아니고, 도시 b까지의 거리를 더 짧게 갱신할 수 있는 경우
        if dist[a] != INF and dist[b] > dist[a] + c:
            # 거리 갱신
            dist[b] = dist[a] + c

# 음수 사이클 확인
# 한 번 더 완화 작업을 수행하여 거리가 갱신되는지 확인
negative_cycle = False
for a, b, c in edges:
    if dist[a] != INF and dist[b] > dist[a] + c:
        # 음수 사이클이 존재함
        # -> 루프가 무한히 돌아가며 시간을 계속 줄일 수 있음
        negative_cycle = True
        break

if negative_cycle:
    print(-1)

# 음수 사이클이 없는 경우
else:
    for i in range(1, N):
        # 도시 i까지의 최단 거리가 무한대인 경우는 도달할 수 없는 경우
        if dist[i] == INF:
            print(-1)
            
        # 도달할 수 있는 경우 최단 거리 출력
        else:
            print(dist[i])