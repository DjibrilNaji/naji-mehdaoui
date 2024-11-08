"use client"
import PartiesList from "@/components/customs/Parties/PartiesList"
import { findAll } from "@/services/PartyService"

import { useQuery } from "@tanstack/react-query"

export default function Parties() {
  const { data, error, isPending } = useQuery({ queryKey: ["parties"], queryFn: findAll })

  if (isPending) return <div>Loading...</div>
  if (error) return <div>Error: {error.message}</div>

  if (data.length > 0) return <PartiesList parties={data} />

  return (
    <div className="h-full justify-center items-center flex text-4xl font-bold">No parties</div>
  )
}
