# 처음에 게이머는 두 발을 중앙에 모으고 있다.
# 지시에 따라 왼쪽 또는 오른쪽 발을 움직인다. 하지만 그의 두 발이 동시에 움직이지는 않는다.

# 중앙에 있던 발이 다른 지점으로 움직일 때, 2의 힘을 사용하게 된다.
# 다른 지점에서 인접한 지점으로 움직일 때는 3의 힘을 사용하게 된다.
# 반대편으로 움직일때는 4의 힘을 사용하게 된다.
# 만약 같은 지점을 한번 더 누른다면, 그때는 1의 힘을 사용하게 된다.

# 모든 지시 사항을 만족하는 데 사용되는 최소의 힘을 출력


# 이동할 때 드는 힘을 계산하는 함수
def get_power(start, target):
    if start == 0:          # 중앙(0)에서 다른 지점으로 이동
        return 2
    if start == target:     # 같은 지점을 다시 누름
        return 1
    if abs(start - target) == 2:  # 반대편으로 이동 (1<->3 또는 2<->4)
        return 4
    return 3                # 인접한 지점으로 이동


# DDR 게임 하자
def DDR(input_data):
    if not input_data:
        return
    
    # 입력이 0 하나만 들어온 경우 예외 처리
    if input_data[0] == 0:
        print(0)
        return
        
    # 마지막 입력인 0은 지시사항이 아니므로 제거
    input_data.pop() 

    INF = float('inf')
    # dp[l][r]: 왼발이 l, 오른발이 r에 있을 때 사용한 최소 힘
    dp = [[INF] * 5 for _ in range(5)]
    dp[0][0] = 0  # 초기 상태: 두 발 모두 중앙(0)에 위치

    # 지시사항을 하나씩 순서대로 처리
    for target in input_data:
        # 이번 지시사항을 밟은 후의 상태를 저장할 새로운 배열
        next_dp = [[INF] * 5 for _ in range(5)]
        
        for l in range(5):
            for r in range(5):
                # 도달할 수 없는 상태(INF)는 계산하지 않고 넘어감
                if dp[l][r] == INF:
                    continue
                
                # 1. 왼발을 목표 지점으로 움직이는 경우
                # (단, 두 발이 같은 지점에 있으면 안 되므로 오른발이 target에 없을 때만)
                if target != r:
                    next_dp[target][r] = min(next_dp[target][r], dp[l][r] + get_power(l, target))
                
                # 2. 오른발을 목표 지점으로 움직이는 경우
                # (단, 왼발이 target에 없을 때만)
                if target != l:
                    next_dp[l][target] = min(next_dp[l][target], dp[l][r] + get_power(r, target))
        
        # 다음 지시사항 처리를 위해 dp 배열을 업데이트
        dp = next_dp

    # 모든 지시사항을 마친 후, 가능한 발 위치 조합 중 가장 적게 든 힘을 찾음
    min_power = INF
    for l in range(5):
        for r in range(5):
            if dp[l][r] < min_power:
                min_power = dp[l][r]
                
    print(min_power)


direction = list(map(int, input().split()))
DDR(direction)