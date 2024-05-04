package pizza.services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pizza.models.Image;
import pizza.models.Point;
import pizza.repositories.ImageRepository;
import pizza.repositories.PointRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class PointService {

    private final PointRepository pointRepository;

    public Point getPointById(Long id) {
        return pointRepository.getById(id);
    }
}
