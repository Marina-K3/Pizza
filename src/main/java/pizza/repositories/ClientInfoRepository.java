package pizza.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pizza.models.ClientInfo;

import java.util.Optional;
@Repository
public interface ClientInfoRepository extends JpaRepository<ClientInfo, Integer> {
    Optional<ClientInfo> findByClientId (int clientId);
    Optional<ClientInfo> findByPhoneNumber(String phoneNumber);
}
