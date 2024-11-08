import Image from "next/image"
import Link from "next/link"

import routes from "@/routes"
import { Party } from "@/types/party"
import { formatSlug } from "@/utils/formatSlug"

interface PartyProps {
  party: Party
}

export default function PartyCard({ party }: PartyProps) {
  return (
    <Link href={routes.party(formatSlug(party.name))} className="w-full p-4">
      <div className="bg-white shadow-lg rounded-lg overflow-hidden">
        <Image
          src="https://github.com/shadcn.png"
          alt="Image de la location"
          width={500}
          height={300}
          className="w-full h-64 object-cover"
        />
        <div className="px-4 py-2">
          <h2 className="text-lg font-semibold truncate">{party.name}</h2>
          <p className="mb-2">{party.type}</p>

          <h2 className="text-gray-600">{party.address}</h2>
          <p className="text-gray-600 mb-2">{party.start_timestamp.toString()}</p>

          <p className="text-lg font-bold text-gray-800">{party.price}€</p>
          <p className="text-gray-800">Reste {party.max_people - party.count_people} place(s)</p>
        </div>
      </div>
    </Link>
  )
}
