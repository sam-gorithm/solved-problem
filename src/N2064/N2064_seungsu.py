# IP 네트워크는 ‘네트워크 주소’와 ‘네트워크 마스크’라는 두 개의 정보로 표현된다.
# 예를 들어 네트워크 주소가 194.85.160.176이고, 네트워크 마스크가 255.255.255.248인 경우를 생각해 보자.
# 이 경우, 이 네트워크에는 194.85.160.176부터 194.85.160.183까지의 8개의 IP 주소가 포함된다.

# 어떤 네트워크에 속해있는 IP 주소들이 주어졌을 때, 네트워크 주소와 네트워크 마스크를 구해라.


n = int(input())
# 각 IP 주소를 4개의 옥텟으로 분리하여 저장
ips = [list(map(int, input().split('.'))) for _ in range(n)]

# 네트워크 주소와 네트워크 마스크 초기화
network_address = [0, 0, 0, 0]
network_mask = [255, 255, 255, 255]

found_different = False  # 다른 비트를 만났는지 여부

# 각 비트마다 검사
for i in range(4):
    for j in range(8):
        # 현재 검사하는 비트 위치
        bit = 1 << (7 - j)

        # 첫 번째 IP의 해당 비트값
        first_bit = ips[0][i] & bit
        
        # 모든 IP가 같은 비트값을 가지는지 확인
        all_same = True
        for k in range(1, n):
            if (ips[k][i] & bit) != first_bit:
                all_same = False
                break
        
        if found_different:
            # 이미 다른 비트를 만났으면, 이 이후의 모든 비트는 마스크에서 0
            network_mask[i] &= ~bit
        elif all_same:
            # 아직 다른 비트를 만나지 않았고, 이 비트가 같으면
            network_address[i] |= first_bit
        else:
            # 처음으로 다른 비트를 만남
            network_mask[i] &= ~bit
            found_different = True

print('.'.join(map(str, network_address)))
print('.'.join(map(str, network_mask)))
