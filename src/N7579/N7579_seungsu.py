# 현재 N개의 앱이 활성화 되어있다고 가정하자. 각 앱은 mi 바이트 만큼의 메모리를 사용하고 있다.
# 또한, 앱 Ai를 비활성화 한 후 다시 실행하려고 할 경우, 추가적으로 들어가는 비용을 ci라고 하자.

# 사용자가 사로운 앱 B를 실행하고자 하여, 추가로 M 바이트의 메모리가 필요할 때,
# M 바이트 이상의 메모리를 추가로 확보하기 위해 비활성화 비용 ci의 합을 최소화하자.

# 1 ≤ N ≤ 100, 1 ≤ M ≤ 10,000,000
# 1 ≤ m1, ..., mN ≤ 10,000,000, 0 ≤ c1, ..., cN ≤ 100


N, M = map(int, input().split())

memories = list(map(int, input().split()))
costs = list(map(int, input().split()))

# dp[i]: 비용 i를 사용했을 때 확보할 수 있는 최대 메모리 양
max_cost = sum(costs)
dp = [0] * (max_cost + 1)

# dp 테이블 채우기
# 각 앱을 비활성화할지 말지 결정
for i in range(N):
    # 역순으로 순회: 큰 비용부터 작은 비용으로

    # ex) costs=[1, 2], memories=[100, 200]
        #   순: dp[3]을 계산할 때 dp[2]를 사용하는데, dp[2]는 이미 앱0으로 업데이트됨
        #           결과적으로 같은 앱을 2번 선택가능
        #   역순: dp[3]을 계산할 때 dp[2](아직 앱0으로 업데이트X)를 사용하므로
        #         앱0과 앱1을 각각 1번씩만 선택

    for cost in range(max_cost, costs[i] - 1, -1):
        dp[cost] = max(dp[cost], dp[cost - costs[i]] + memories[i])

# M 바이트 이상을 확보할 수 있는 최소 비용 찾기
for cost in range(max_cost + 1):
    if dp[cost] >= M:
        print(cost)
        break
