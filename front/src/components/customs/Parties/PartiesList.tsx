import { Party } from "@/types/party"
import PartyCard from "./PartyCard"

interface PartiesListProps {
  parties: Party[]
}

export default function PartiesList({ parties }: PartiesListProps) {
  return (
    <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-2 mx-4">
      {parties.map((party, index) => (
        <PartyCard party={party} key={index} />
      ))}
    </div>
  )
}
