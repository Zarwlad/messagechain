package ru.zarwlad.uth.generated.sn.storeddata.model.hierarchy;

import lombok.*;
import ru.zarwlad.uth.generated.sn.storeddata.model.Batch;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@EqualsAndHashCode(of = "code")
public class HieEntry implements Comparable{
    private String code;
    private Boolean sscc;
    private Batch batch;
    private List<HieEntry> nested;

    private HieEntry(String code, Boolean sscc, Batch batch, List<HieEntry> nested) {
        this.code = code;
        this.sscc = sscc;
        this.batch = batch;
        this.nested = nested;
    }

    public static List<HieEntry> createSgtins(List<String> codes, Batch batch){
        List<HieEntry> entries = new ArrayList<>();
        codes.forEach(x -> entries.add(new HieEntry(x, false, batch, null)));
        return entries;
    }

    public static HieEntry createSscc (String code, List<HieEntry> nestedCodes){
        return new HieEntry(code, true, null, nestedCodes);
    }

    public void addNestedNodes (HieEntry node, List<HieEntry> nestedNodes) throws Exception {
        if (!node.getSscc())
            throw new Exception("Запрещено вкладывать не в SSCC, код " + node.getCode());
        else {
            Queue<HieEntry> queue = new PriorityQueue<>();
            queue.add(this);

            while (!queue.isEmpty()){
                HieEntry e = queue.poll();
                if (e.equals(node)) {
                    e.getNested().addAll(nestedNodes);
                    break;
                }
                else
                    queue.addAll(node.getNested());
            }
        }
    }

    public void removeInnerNodeWithNested(HieEntry node){
        Queue<HieEntry> queue = new PriorityQueue<>();
        queue.add(this);

        while (!queue.isEmpty()){
            HieEntry e = queue.poll();
            if(e.getNested().contains(e)){
                e.getNested().remove(e);
                break;
            }
            else
                queue.addAll(e.getNested());
        }
    }

    public Set<Batch> getAllBatches(){
        Set<Batch> batches = new HashSet<>();

        this.getNested()
                .stream()
                .flatMap(x -> x.getNested().stream())
                .forEach(x -> batches.add(x.getBatch()));

        return batches;
    }

    public List<HieEntry> getAllSgtins(){
        return this.getNested()
                .stream()
                .flatMap( x -> x.getNested().stream())
                .collect(Collectors.toList());
    }

    @Override
    public int compareTo(Object o) {
        HieEntry e = (HieEntry) o;
        return this.getCode().compareTo(e.getCode());
    }
}
