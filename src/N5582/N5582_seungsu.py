# 첫째 줄과 둘째 줄에 문자열이 주어진다. 문자열은 대문자로 구성되어 있으며, 길이는 1 이상 4000 이하이다.
# 첫째 줄에 두 문자열에 모두 포함 된 부분 문자열 중 가장 긴 것의 길이를 출력한다.


str1 = input().strip()
str2 = input().strip()

# 첫 번째와 두 번째 문자열의 길이
len1 = len(str1)
len2 = len(str2)

# dp[i][j]: str1의 i번째 문자와 str2의 j번째 문자가 일치할 때
# 얼마나 많은 연속된 공통 문자열이 있었는지를 저장
dp = [[0] * (len2 + 1) for _ in range(len1 + 1)]

max_length = 0 # 공통부분문자열의 최대 길이

# dp 테이블 채우기
for i in range(1, len1 + 1):
    for j in range(1, len2 + 1):
        # str1의 (i-1)번째 문자와 str2의 (j-1)번째 문자 비교
        if str1[i - 1] == str2[j - 1]:
            # 문자가 일치하면 이전 공통 길이에 1을 더함
            # dp[i-1][j-1]에서 연속된 길이 + 1
            dp[i][j] = dp[i - 1][j - 1] + 1

            # 최대 길이 업데이트
            max_length = max(max_length, dp[i][j])
            
        else:
            # 문자가 일치하지 않으면 0으로 초기화 (연속성 끊김)
            dp[i][j] = 0

print(max_length)
