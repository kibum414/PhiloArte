package shop.parkkibeom.api.art.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.parkkibeom.api.art.domain.*;
import shop.parkkibeom.api.art.repository.ArtFileRepository;
import shop.parkkibeom.api.artist.domain.Artist;
import shop.parkkibeom.api.category.domain.Category;
import shop.parkkibeom.api.common.service.AbstractService;
import shop.parkkibeom.api.art.repository.ArtRepository;
import shop.parkkibeom.api.resume.domain.Resume;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RequiredArgsConstructor
@Service
public class ArtServiceImpl implements ArtService {

    private final ArtRepository artRepository;
    private final ArtFileRepository artFileRepository;

    @Transactional
    @Override
    public Long register(ArtDto artDto) {
        Art art = dtoToEntity(artDto);

        artRepository.save(art);

        List<ArtFileDto> artFileDtos = artDto.getFiles();

        if (artFileDtos != null && artFileDtos.size() > 0) {
            artFileDtos.forEach(artFileDto -> {
                ArtFile artFile = dtoToEntityFiles(artFileDto);
                artFile.setArt(art);
                artFileRepository.save(artFile);
            });
        }

        return art.getArtId();
    }

    @Override
    public PageResultDTO<ArtDto, Object[]> getArtList(PageRequestDTO pageRequestDTO) {
        Function<Object[], ArtDto> fn = (entity ->
                entityToDto((Art) entity[0], (Artist) entity[1], (Category) entity[2], (Resume) entity[3], (ArtFile) entity[4]));

        Page<Object[]> result = artRepository.getArts(pageRequestDTO.getPageable(Sort.by("artId").descending()));

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public ArtDto get(Long artId) {
//        Object result = artRepository.getArtsByArtId(artId);
//
//        Object[] arr = (Object[]) result;
//
//        return entityToDto((Art) arr[0], (Artist) arr[1], (Category) arr[2], (Resume) arr[3], (ArtFile) arr[4]);
        return null;
    }

    @Override
    public List<ArtDto> getAllArts() {
//        return artRepository.getAllArts().stream().map(this::entityToDto).collect(Collectors.toList());
        return null;
    }

    // 전체 목록 -> 대표 이미지만 보이면 됨
    // 상세 -> 해당 artId에 맞는 파일들 전부 다 보여야 됨
    @Transactional
    @Override
    public Page<ArtDto> getAllArtsPaging() {
        Pageable pageable = PageRequest.of(0, 10);

//        return artRepository.getAllArtsPaging(pageable).map(this::entityToDto);
        return null;
    }

    @Override
    public ArtDto getArtsByArtId(Long artId) {
//        return entityToDto(artRepository.getArtByArtId(artId));
        return null;
    }

    @Override
    public List<ArtFile> getFilesByArtId(Long artId) {
        return artFileRepository.getFilesByArtId(artId);
    }

    @Transactional
    @Override
    public Long delete(ArtDto artDto) {
        artRepository.deleteById(artDto.getArtId());

        return (findById(artDto.getArtId()).orElse(null) == null) ? 1L : 0L;
    }




    @Override
    public Long count() {
        return artRepository.count();
    }

    @Override
    public Boolean existsById(long id) {
        return artRepository.existsById(id);
    }

    @Override
    public List<ArtDto> findAll() {
        return null;
    }

    @Override
    public Optional<ArtDto> findById(long id) {
        return null;
    }

    @Override
    public Optional<ArtDto> getOne(long id) {
        return null;
    }
}
